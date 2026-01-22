import pytest
from app.database import SessionLocal
from app import models

@pytest.fixture()
def db():
    session = SessionLocal()

    # Полная очистка таблицы перед тестом
    session.query(models.Track).delete()
    session.commit()

    yield session

    # откат и закрытие
    session.rollback()
    session.close()
