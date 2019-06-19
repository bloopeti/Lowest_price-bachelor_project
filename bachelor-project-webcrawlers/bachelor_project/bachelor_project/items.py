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


def process_float_or_int(value):
    try:
        return eval(value)
    except:
        return value


class ProductItem(scrapy.Item):
    crawled_name = scrapy.Field()
    url = scrapy.Field()
    domain = scrapy.Field()
    price = scrapy.Field()  # input_processor=MapCompose(lambda x: process_float_or_int(x)))
    currency = scrapy.Field()
    brand = scrapy.Field()
