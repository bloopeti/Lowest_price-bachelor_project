# -*- coding: utf-8 -*-
import scrapy

from items import FillerItem  # it would say ..items but we have to use relative path to crawler_caller not the spider


class EmagSpider(scrapy.Spider):
    name = 'emag'
    allowed_domains = ['emag.ro']

    def start_requests(self):
        yield scrapy.Request(self.start_url)

    def parse(self, response):
        price = response.css('div.product-page-pricing p.product-new-price::text').get().replace('.', '') + '.' + \
                response.css('div.product-page-pricing p.product-new-price sup::text').get()
        currency = response.css('div.product-page-pricing p.product-new-price span::text').get()
        crawled_name = response.css('h1.page-title::text').get()
        brand = response.css('h1.page-title::text').re('Telefon mobil (\w*)')[0]

        if currency.upper() in ('LEI', 'RON'):
            currency = 'RON'

        product = FillerItem()
        product['productId'] = self.product_id
        product['domain'] = self.name

        product['price'] = float(price.strip())
        product['currency'] = currency.strip()
        product['crawled_name'] = crawled_name.strip()
        product['brand'] = brand.strip()

        yield product
