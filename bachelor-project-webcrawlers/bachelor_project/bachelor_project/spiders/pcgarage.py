# -*- coding: utf-8 -*-
import scrapy

from items import FillerItem


class PcgarageSpider(scrapy.Spider):
    name = 'pcgarage'
    allowed_domains = ['pcgarage.ro']

    # def start_requests(self):
    #     yield scrapy.Request(self.start_url)

    def parse(self, response):
        price = response.css('p.ps-sell-price span:not(.price-text)::text').re('^(.*) [a-zA-Z]*')[0]
        currency = response.css('p.ps-sell-price span:not(.price-text)::text').re('^.* ([a-zA-Z]*)')[0]
        crawled_name = response.css('h1.p-name::text').get()
        brand = response.css('h1.p-name::text').re('^Smartphone (\w*)')[0]

        if currency.upper() in ('LEI', 'RON'):
            currency = 'RON'

        product = FillerItem()
        product['productUrl'] = response.request.url
        # product['productId'] = self.product_id
        product['domain'] = self.name

        product['price'] = float(price.strip().replace('.', '').replace(',', '.'))
        product['currency'] = currency.strip()
        product['crawled_name'] = crawled_name.strip()
        product['brand'] = brand.strip()

        yield product
