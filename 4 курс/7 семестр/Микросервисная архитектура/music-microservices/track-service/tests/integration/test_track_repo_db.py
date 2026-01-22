import pytest
from uuid import uuid4
from sqlalchemy.orm import Session
from app import models


def test_empty_tracks(db: Session):
    tracks = db.query(models.Track).all()
    assert tracks == []


def test_create_track(db: Session):
    track = models.Track(
        id=uuid4(),
        title="Track1",
        artist="Artist",
        album="Album",
        genre="Pop",
        duration=200
    )
    db.add(track)
    db.commit()

    found = db.query(models.Track).filter_by(title="Track1").first()
    assert found is not None
    assert found.artist == "Artist"


def test_get_track_by_id(db: Session):
    track = models.Track(
        id=uuid4(),
        title="UniqueSong",
        artist="Band",
        genre="Rock",
        duration=150
    )
    db.add(track)
    db.commit()

    found = db.get(models.Track, track.id)
    assert found is not None
    assert found.title == "UniqueSong"
