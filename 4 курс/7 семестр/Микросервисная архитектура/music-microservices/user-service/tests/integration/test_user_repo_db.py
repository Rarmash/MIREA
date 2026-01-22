import pytest
from uuid import uuid4
from sqlalchemy.orm import Session
from app import models
from app.database import SessionLocal, engine

def test_empty_users(db):
    users = db.query(models.User).all()
    assert users == []

def test_create_user(db):
    user = models.User(
        id=uuid4(),
        email="int_test@mail.com",
        password_hash="123",
        display_name="Test",
        role="USER"
    )
    db.add(user)
    db.commit()

    q = db.query(models.User).filter_by(email="int_test@mail.com").first()
    assert q is not None
    assert q.email == "int_test@mail.com"

def test_unique_email(db):
    u1 = models.User(
        id=uuid4(),
        email="dup@mail.com",
        password_hash="123",
        display_name="User1"
    )
    db.add(u1)
    db.commit()

    u2 = models.User(
        id=uuid4(),
        email="dup@mail.com",
        password_hash="123",
        display_name="User2"
    )

    with pytest.raises(Exception):
        db.add(u2)
        db.commit()
