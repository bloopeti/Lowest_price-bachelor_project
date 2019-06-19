from scrapy.crawler import CrawlerProcess
from items import ProductItem
from scrapy.utils.project import get_project_settings

import requests
import json
import sys

if len(sys.argv) == 2:
    domain_name = sys.argv[1]

    url = "http://localhost:9906/app/productUrl/get/" + domain_name

    response = requests.get(url=url)
    data = json.loads(response.text)

    scrapy_settings = get_project_settings()

    process = CrawlerProcess(scrapy_settings)

    result_product = ProductItem()

    if response.ok:
        for productUrl in data:
            process.crawl(domain_name,
                          start_url=productUrl['url'],
                          result=result_product)
            process.start()

            print('---------------\n')
            print(result_product)  # alternatively, send a request to the webservice in an item pipeline to add to db
    else:
        print("Couldn't access the webservice! Status code: %d" % response.status_code)
else:
    print("Incorrect number of arguments!\nTwo arguments are required: this file's path and the domain you wish to "
          "crawl and update")
