import json
import sys

import requests
from scrapy.crawler import CrawlerProcess
from scrapy.utils.project import get_project_settings

if len(sys.argv) == 2:
    domain_name = sys.argv[1]

    url = "http://localhost:9906/app/productUrl/get/" + domain_name

    response = requests.get(url=url)
    data = json.loads(response.text)

    scrapy_settings = get_project_settings()

    process = CrawlerProcess(scrapy_settings)

    if response.ok:
        for productUrl in data:
            process.crawl(domain_name,
                          start_url=productUrl['url'],
                          product_id=productUrl['productId'])
            process.start()
    else:
        print("\tCouldn't access the webservice! Status code: %d" % response.status_code)
else:
    print("\tIncorrect number of arguments!\nTwo arguments are required: this file's path and the domain you wish to "
          "crawl and update")
