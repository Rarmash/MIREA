from fastapi import FastAPI, Depends, HTTPException
from sqlalchemy.orm import Session
from pydantic import BaseModel
from typing import List, Optional
from app import models
from app import database
import uuid
import httpx

app = FastAPI(title="Track Service", version="1.0.0")


class TrackCreate(BaseModel):
    title: str
    artist: str
    album: Optional[str] = None
    genre: str
    duration: int
    added_by: str


class TrackResponse(BaseModel):
    id: str
    title: str
    artist: str
    album: Optional[str]
    genre: str
    duration: int
    created_at: str


async def verify_user(user_id: str) -> bool:
    try:
        async with httpx.AsyncClient() as client:
            response = await client.get(f"http://user-service:8000/users/{user_id}")
            return response.status_code == 200
    except Exception:
        return False


async def is_user_admin(user_id: str) -> bool:
    try:
        async with httpx.AsyncClient() as client:
            response = await client.get(f"http://user-service:8000/users/{user_id}")
            if response.status_code == 200:
                user_data = response.json()
                return user_data.get("role") == "ADMIN"
            return False
    except Exception:
        return False


@app.on_event("startup")
def startup():
    models.Base.metadata.create_all(bind=database.engine)


@app.post("/tracks", response_model=TrackResponse)
async def create_track(track: TrackCreate, db: Session = Depends(database.get_db)):
    user_exists = await verify_user(track.added_by)
    if not user_exists:
        raise HTTPException(status_code=400, detail="User not found")

    is_admin = await is_user_admin(track.added_by)
    if not is_admin:
        raise HTTPException(status_code=403, detail="Only administrators can add tracks")

    db_track = models.Track(
        title=track.title,
        artist=track.artist,
        album=track.album,
        genre=track.genre,
        duration=track.duration
    )

    db.add(db_track)
    db.commit()
    db.refresh(db_track)

    return TrackResponse(
        id=str(db_track.id),
        title=db_track.title,
        artist=db_track.artist,
        album=db_track.album,
        genre=db_track.genre,
        duration=db_track.duration,
        created_at=db_track.created_at.isoformat()
    )


@app.get("/tracks/{track_id}", response_model=TrackResponse)
def get_track(track_id: str, db: Session = Depends(database.get_db)):
    try:
        track_uuid = uuid.UUID(track_id)
    except ValueError:
        raise HTTPException(status_code=400, detail="Invalid track ID format")

    track = db.query(models.Track).filter(models.Track.id == track_uuid).first()
    if not track:
        raise HTTPException(status_code=404, detail="Track not found")

    return TrackResponse(
        id=str(track.id),
        title=track.title,
        artist=track.artist,
        album=track.album,
        genre=track.genre,
        duration=track.duration,
        created_at=track.created_at.isoformat()
    )


if __name__ == "__main__":
    import uvicorn

    uvicorn.run(app, host="0.0.0.0", port=8001)