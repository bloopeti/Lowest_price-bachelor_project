# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://doc.scrapy.org/en/latest/topics/item-pipeline.html


class BachelorProjectPipeline(object):
    def process_item(self, item, spider):
        return item


class RestPipeline(object):
    def process_item(self, item, spider):
        print('\n custom pipe \n')
        print(item)
        print('\n custom pipe \n')
        return item
