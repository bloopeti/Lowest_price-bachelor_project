# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://doc.scrapy.org/en/latest/topics/item-pipeline.html

import requests
from product_loader import load_product


class BachelorProjectPipeline(object):
    def process_item(self, item, spider):
        return item


class RestPipeline(object):
    def process_item(self, item, spider):
        print('\n custom pipe \n')
        product_db = load_product(item['productId'], item['domain'])

        print(product_db)

        print('\ndownloaded:\n')

        print(item)
        print('\n custom pipe \n')

# check if fields are different and assign new data to product_db and post it through the webservice to database

        return item
