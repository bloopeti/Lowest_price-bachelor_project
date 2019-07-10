import json
import sys

import requests
from scrapy.crawler import CrawlerProcess, Crawler
from scrapy.utils.project import get_project_settings

# class DomainCrawlerScript:
#
#     def __init__(self):
#         self.crawler = CrawlerProcess(get_project_settings())
#         self.crawler.install()
#         self.crawler.configure()
#
#         # self.crawlr = Crawler(get_project_settings())
#         # self.crawlr.conf
#
#     def _crawl(self, domain_pk):
#         domain = Domain.objects.get(
#             pk = domain_pk,
#         )
#         urls = []
#         for page in domain.pages.all():
#             urls.append(page.url())
#         self.crawler.crawl(DomainSpider(urls))
#         self.crawler.start()
#         self.crawler.stop()
#
#     def crawl(self, domain_pk):
#         p = Process(target=self._crawl, args=[domain_pk])
#         p.start()
#         p.join()

existing_spiders = ['emag', 'pcgarage']

if len(sys.argv) == 2:
    domain_name = sys.argv[1]

    if domain_name.strip() in existing_spiders:
        url = "http://localhost:9906/app/productUrl/get/" + domain_name

        response = requests.get(url=url)
        data = json.loads(response.text)

        scrapy_settings = get_project_settings()

        start_urls = []
        for productUrl in data:
            start_urls.append(productUrl['url'])

        if response.ok:
            # for productUrl in data:
            process = CrawlerProcess(scrapy_settings)

            process.crawl(domain_name,
                          start_urls=start_urls)
            # start_url=productUrl['url'],
            # product_id=productUrl['productId'])
            process.start()
        else:
            print("\tCouldn't access the webservice! Status code: %d" % response.status_code)
    else:
        print("\tThe given domain is not supported!\n\tSupported domains are: ", ', '.join(existing_spiders))
else:
    print("\tIncorrect number of arguments!\n\tTwo arguments are required: this file's path and the domain you wish to "
          "crawl and update")
