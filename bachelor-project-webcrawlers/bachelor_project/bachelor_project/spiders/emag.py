# -*- coding: utf-8 -*-
import scrapy

from items import ProductItem  # it would say ..items but we have to use relative path to crawler_caller not the spider


class EmagSpider(scrapy.Spider):
    name = 'emag'
    allowed_domains = ['emag.ro']

    def start_requests(self):
        yield scrapy.Request(self.start_url)

    def parse(self, response):
        filename = 'emag.html'
        with open(filename, 'wb') as f:
            f.write(response.body)

        price = response.css('div.product-page-pricing p.product-new-price::text').get().replace('.', '') + '.' + response.css(
            'div.product-page-pricing p.product-new-price sup::text').get()
        currency = response.css('div.product-page-pricing p.product-new-price span::text').get()
        crawled_name = response.css('h1.page-title::text').get()
        brand = response.css('h1.page-title::text').re('Telefon mobil (\w*)')[0]

        if currency.lower() == 'lei':
            currency = 'RON'

        product = ProductItem()
        product['price'] = float(price.strip())
        product['currency'] = currency.strip()
        product['crawled_name'] = crawled_name.strip()
        product['brand'] = brand.strip()

        self.result['price'] = float(price.strip())
        self.result['currency'] = currency.strip()

        yield product