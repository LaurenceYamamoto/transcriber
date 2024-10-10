import os

provider = os.getenv("TRANSCRIBER_PROVIDER", os.getenv("AI_PROVIDER", "google_cloud"))
model = os.getenv("TRANSCRIBER_MODEL", "default")

def trnscribe(file: str) -> str:
    if provider == "google_cloud":
        return "google_cloud"
    else:
        raise ValueError(f"Invalid provider: {provider}")

def transcribe_google_cloud(file: str) -> str:
