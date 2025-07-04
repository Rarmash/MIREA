from pymongo import MongoClient

client = MongoClient("mongodb://localhost:30001/?directConnection=true")
db = client["accounting"]
