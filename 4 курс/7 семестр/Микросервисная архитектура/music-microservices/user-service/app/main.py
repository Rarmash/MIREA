from fastapi import FastAPI, Depends, HTTPException
from sqlalchemy.orm import Session
from pydantic import BaseModel
from typing import Optional
from app import models
from app import database
import uuid

app = FastAPI(title="User Service", version="1.0.0")


class UserCreate(BaseModel):
    email: str
    password: str
    display_name: str


class UserResponse(BaseModel):
    id: str
    email: str
    display_name: str
    role: str
    created_at: str


@app.on_event("startup")
def startup():
    models.Base.metadata.create_all(bind=database.engine)


@app.post("/users/register", response_model=UserResponse)
def register_user(user: UserCreate, db: Session = Depends(database.get_db)):
    existing_user = db.query(models.User).filter(models.User.email == user.email).first()
    if existing_user:
        raise HTTPException(status_code=400, detail="Email already registered")

    db_user = models.User(
        email=user.email,
        password_hash=user.password,
        display_name=user.display_name,
        role="USER"
    )

    db.add(db_user)
    db.commit()
    db.refresh(db_user)

    return UserResponse(
        id=str(db_user.id),
        email=db_user.email,
        display_name=db_user.display_name,
        role=db_user.role,
        created_at=db_user.created_at.isoformat()
    )


@app.get("/users/{user_id}", response_model=UserResponse)
def get_user(user_id: str, db: Session = Depends(database.get_db)):
    try:
        user_uuid = uuid.UUID(user_id)
    except ValueError:
        raise HTTPException(status_code=400, detail="Invalid user ID format")

    user = db.query(models.User).filter(models.User.id == user_uuid).first()
    if not user:
        raise HTTPException(status_code=404, detail="User not found")

    return UserResponse(
        id=str(user.id),
        email=user.email,
        display_name=user.display_name,
        role=user.role,
        created_at=user.created_at.isoformat()
    )

@app.get("/users/health")
def health():
    return {"status": "ok"}

@app.patch("/debug/set_admin/{user_id}")
def set_admin(user_id: str, db: Session = Depends(database.get_db)):
    user = db.query(models.User).filter(models.User.id == user_id).first()
    if not user:
        raise HTTPException(status_code=404, detail="User not found")

    user.role = "ADMIN"
    db.commit()
    return {"status": "ok"}

if __name__ == "__main__":
    import uvicorn

    uvicorn.run(app, host="0.0.0.0", port=8000)