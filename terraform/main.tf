// note that terraform state is being managed locally at this time
provider "google" {
    credentials = ""
    project = "lims-mdonahue-sb"
    region = "us-central"
}

resource "google_pubsub_topic" "topleveltopic" {
    name = "topic-from-name-argument"
}