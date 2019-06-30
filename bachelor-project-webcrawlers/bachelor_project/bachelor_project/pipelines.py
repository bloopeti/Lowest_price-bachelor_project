# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://doc.scrapy.org/en/latest/topics/item-pipeline.html

import requests
import json
from product_loader import load_product_url


class BachelorProjectPipeline(object):
    def process_item(self, item, spider):
        return item


class RestPipeline(object):
    def process_item(self, item, spider):
        # print('\n custom pipe \n')
        product_db = load_product_url(item['productUrl'], item['domain'])
        #
        # print(product_db)
        #
        # print('\ndownloaded:\n')
        # print(item)
        #
        # print('\n custom pipe \n')

        # if product_db['id'] == item['productId']:
        if product_db['urls']['url'] == item['productUrl']:
            if product_db['urls']['domain'] == item['domain']:
                if product_db['name'] in item['crawled_name']:
                    parts_to_update = []
                    if product_db['productDetails']['brand'] != item['brand']:
                        product_db['productDetails']['brand'] = item['brand']
                        parts_to_update.append('productDetails')
                    if product_db['urls']['productPriceForUrl']['currency'] != item['currency']:
                        product_db['urls']['productPriceForUrl']['currency'] = item['currency']
                        parts_to_update.append('productPriceForUrl')
                    if product_db['urls']['productPriceForUrl']['price'] != item['price']:
                        product_db['urls']['productPriceForUrl']['price'] = item['price']
                        parts_to_update.append('productPriceForUrl')

                    # print(parts_to_update)

                    # print(product_db['urls']['productPriceForUrl'])

                    parts_to_update = list(set(parts_to_update))
                    url = "http://localhost:9906/app/%s/update/"
                    for part in parts_to_update:
                        data = {}
                        if part == 'productDetails':
                            data = json.loads(str(product_db['productDetails']).replace("'", '"'))
                        if part == 'productPriceForUrl':
                            data = json.loads(str(product_db['urls']['productPriceForUrl']).replace("'", '"'))
                        print("\tAttempting to post: '%s'" % data)
                        response = requests.post(url=url % part, json=data)
                        if not response.ok:
                            print("\tDidn't manage to update %s! Status code: %d. Message: '%s'." % (part, response.status_code, response.content))
                        else:
                            print("\tSuccess updating %s! Response message: '%s'." % (part, response.content))
                else:
                    print("\tProduct name (database / crawled) doesn't match!")
            else:
                print("\tDomains (database / crawled) don't match!")
        else:
            print("\tProduct id (database / crawled) don't match!")

        return item
