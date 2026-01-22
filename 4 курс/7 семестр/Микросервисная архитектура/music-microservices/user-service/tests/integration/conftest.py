import pytest
from app.database import SessionLocal
from app import models

@pytest.fixture()
def db():
    session = SessionLocal()

    # очистить таблицу целиком
    session.query(models.User).delete()
    session.commit()

    yield session

    session.rollback()
    session.close()
