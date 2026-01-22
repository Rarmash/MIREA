import time
import pytest
import requests
from uuid import uuid4

time.sleep(2)
BASE_URL = "http://localhost:8000"

@pytest.fixture()
def user_data():
    return {
        "email": f"test_{uuid4().hex[:6]}@mail.com",
        "password": "pass123",
        "display_name": "TestUser"
    }

def test_register_user_success(user_data):
    response = requests.post(f"{BASE_URL}/users/register", json=user_data)
    assert response.status_code == 200
    json = response.json()

    assert json["email"] == user_data["email"]
    assert json["display_name"] == user_data["display_name"]
    assert json["role"] == "USER"
    assert "id" in json

def test_register_user_duplicate(user_data):
    requests.post(f"{BASE_URL}/users/register", json=user_data)
    response = requests.post(f"{BASE_URL}/users/register", json=user_data)

    assert response.status_code == 400
    assert response.json()["detail"] == "Email already registered"

def test_get_user_success(user_data):
    created = requests.post(f"{BASE_URL}/users/register", json=user_data).json()
    user_id = created["id"]

    response = requests.get(f"{BASE_URL}/users/{user_id}")
    assert response.status_code == 200
    json = response.json()

    assert json["id"] == user_id
    assert json["email"] == user_data["email"]

def test_get_user_not_found():
    random_id = uuid4()
    response = requests.get(f"{BASE_URL}/users/{random_id}")
    assert response.status_code == 404
    assert response.json()["detail"] == "User not found"

def test_get_user_invalid_id():
    response = requests.get(f"{BASE_URL}/users/12345")
    assert response.status_code == 400
    assert response.json()["detail"] == "Invalid user ID format"
