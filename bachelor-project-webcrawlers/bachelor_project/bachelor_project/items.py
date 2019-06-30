# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# https://doc.scrapy.org/en/latest/topics/items.html

import scrapy


class BachelorProjectItem(scrapy.Item):
    # define the fields for your item here like:
    # name = scrapy.Field()
    pass


class FillerItem(scrapy.Item):
    crawled_name = scrapy.Field()
    price = scrapy.Field()
    currency = scrapy.Field()
    brand = scrapy.Field()
    productId = scrapy.Field()
    productUrl = scrapy.Field()
    domain = scrapy.Field()


class ProductDetailsItem(scrapy.Item):
    id = scrapy.Field()
    productId = scrapy.Field()
    brand = scrapy.Field()


class ProductPriceForUrlItem(scrapy.Item):
    id = scrapy.Field()
    price = scrapy.Field()
    currency = scrapy.Field()
    productUrlId = scrapy.Field()


class ProductUrlItem(scrapy.Item):
    id = scrapy.Field()
    url = scrapy.Field()
    domain = scrapy.Field()
    productId = scrapy.Field()
    productPriceForUrl = scrapy.Field()


class ProductItem(scrapy.Item):
    id = scrapy.Field()
    name = scrapy.Field()
    productDetails = scrapy.Field()
    urls = scrapy.Field()
