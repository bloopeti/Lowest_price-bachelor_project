from items import *

import requests
import json


def load_product(product_id, domain):
    url = "http://localhost:9906/app/product/get/" + str(product_id)
    response = requests.get(url=url)
    if response.ok:
        product_json = json.loads(response.text)
        print(product_json)

        # product_details_item = ProductDetailsItem()
        # product_details_item['id'] = product_json['productDetails']['id']
        # product_details_item['productId'] = product_json['productDetails']['productId']
        # product_details_item['brand'] = product_json['productDetails']['brand']
        #
        # needed_url = product_json['urls'][0]
        # for product_url in product_json['urls']:
        #     if product_url['domain'] == domain:
        #         needed_url = product_url
        #
        # product_price_for_url_item = ProductPriceForUrlItem()
        # product_price_for_url_item['id'] = needed_url['productPriceForUrl']['id']
        # product_price_for_url_item['price'] = needed_url['productPriceForUrl']['price']
        # product_price_for_url_item['currency'] = needed_url['productPriceForUrl']['currency']
        # product_price_for_url_item['productUrlId'] = needed_url['productPriceForUrl']['productUrlId']
        #
        # product_url_item = ProductUrlItem()
        # product_url_item['productPriceForUrl'] = product_price_for_url_item
        # product_url_item['id'] = needed_url['id']
        # product_url_item['url'] = needed_url['url']
        # product_url_item['domain'] = needed_url['domain']
        # product_url_item['productId'] = needed_url['productId']
        #
        # product_item = ProductItem()
        # product_item['productDetails'] = product_details_item
        # product_item['urls'] = product_url_item
        # product_item['id'] = product_json['id']
        # product_item['name'] = product_json['name']
        #
        # print(product_item)
        # print('\n-----\n')
        # product_item['urls'] = ProductUrlItem()

        product_item = ProductItem(product_json)

        url_list = product_item['urls']
        needed_url = url_list[0]
        for product_url in product_json['urls']:
            if product_url['domain'] == domain:
                needed_url = product_url
                break

        product_item['urls'] = needed_url

        product_dict = dict(product_item)
        product_dict['productDetails'].pop('productId', None)
        product_dict['urls'].pop('productId', None)
        product_dict['urls']['productPriceForUrl'].pop('productUrlId', None)
        product_item = ProductItem(product_dict)

        return product_item


if __name__ == '__main__':
    # test1.py executed as script
    # do something
    print(load_product(1, 'emag'))
