# -*- coding: utf-8 -*-
import scrapy

from items import ProductItem


class PcgarageSpider(scrapy.Spider):
    name = 'pcgarage'
    allowed_domains = ['pcgarage.ro']
    start_urls = ["https://www.pcgarage.ro/smartphone/apple/iphone-x-hexa-core-64gb-3gb-ram-single-sim-4g-tri-camera-space-grey/"]

    def parse(self, response):
        filename = 'pcgarage.html'
        with open(filename, 'wb') as f:
            f.write(response.body)

        price = response.css('p.ps-sell-price span:not(.price-text)::text').re('^(.*) [a-zA-Z]*')[0]
        currency = response.css('p.ps-sell-price span:not(.price-text)::text').re('^.* ([a-zA-Z]*)')[0]
        crawled_name = response.css('h1.p-name::text').get()
        brand = response.css('h1.p-name::text').re('^Smartphone (\w*)')[0]

        product = ProductItem()

        product['price'] = float(price.strip().replace('.', '').replace(',', '.'))
        product['currency'] = currency.strip()
        product['crawled_name'] = crawled_name.strip()
        product['brand'] = brand.strip()

        yield product
