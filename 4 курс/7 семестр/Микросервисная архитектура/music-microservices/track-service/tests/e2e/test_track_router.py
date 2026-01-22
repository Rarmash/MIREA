import pytest
import requests
from uuid import uuid4

USER_URL = "http://localhost:8000"
TRACK_URL = "http://localhost:8001"


@pytest.fixture()
def admin_user():
    """Создаём пользователя + делаем его админом."""

    data = {
        "email": f"admin_{uuid4().hex[:6]}@mail.com",
        "password": "pass123",
        "display_name": "AdminUser"
    }

    # 1. регистрация
    r = requests.post(f"{USER_URL}/users/register", json=data)
    assert r.status_code == 200
    user = r.json()
    user_id = user["id"]

    # 2. делаем ADMIN в базе через debug endpoint
    r2 = requests.patch(f"{USER_URL}/debug/set_admin/{user_id}")
    assert r2.status_code == 200

    return user_id


@pytest.fixture()
def regular_user():
    data = {
        "email": f"user_{uuid4().hex[:6]}@mail.com",
        "password": "pass123",
        "display_name": "Regular"
    }
    r = requests.post(f"{USER_URL}/users/register", json=data)
    assert r.status_code == 200
    return r.json()["id"]


def test_create_track_success(admin_user):
    track = {
        "title": "TestSong",
        "artist": "Tester",
        "album": "Album",
        "genre": "Rock",
        "duration": 180,
        "added_by": admin_user
    }

    r = requests.post(f"{TRACK_URL}/tracks", json=track)
    assert r.status_code == 200
    resp = r.json()

    assert resp["title"] == "TestSong"
    assert resp["artist"] == "Tester"
    assert resp["genre"] == "Rock"
    assert "id" in resp


def test_create_track_user_not_found():
    data = {
        "title": "BadSong",
        "artist": "None",
        "genre": "Pop",
        "duration": 120,
        "added_by": str(uuid4())  # несуществующий ID
    }
    r = requests.post(f"{TRACK_URL}/tracks", json=data)
    assert r.status_code == 400
    assert r.json()["detail"] == "User not found"


def test_create_track_not_admin(regular_user):
    data = {
        "title": "ForbiddenSong",
        "artist": "Artist",
        "genre": "Rap",
        "duration": 150,
        "added_by": regular_user
    }

    r = requests.post(f"{TRACK_URL}/tracks", json=data)
    assert r.status_code == 403
    assert r.json()["detail"] == "Only administrators can add tracks"


def test_get_track_success(admin_user):
    data = {
        "title": "GoodSong",
        "artist": "GoodArtist",
        "genre": "Jazz",
        "duration": 200,
        "added_by": admin_user
    }

    created = requests.post(f"{TRACK_URL}/tracks", json=data).json()
    track_id = created["id"]

    r = requests.get(f"{TRACK_URL}/tracks/{track_id}")
    assert r.status_code == 200
    assert r.json()["id"] == track_id


def test_get_track_invalid_id():
    r = requests.get(f"{TRACK_URL}/tracks/not-a-uuid")
    assert r.status_code == 400
    assert r.json()["detail"] == "Invalid track ID format"
