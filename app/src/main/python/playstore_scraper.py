from google_play_scraper import search

class AppData:
    def __init__(self, rank, title, package):
        self.rank = rank
        self.title = title
        self.package = package

def search_apps(keyword, country="us", lang="en", n_hits=20):
    results = search(
        keyword,
        lang=lang,
        country=country,
        n_hits=n_hits
    )

    apps = []

    for index, app in enumerate(results, start=1):
        apps.append(AppData(
            index,
            app["title"],
            app["appId"]
        ))

    return apps