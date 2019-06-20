import {ProductDetails} from './productDetails';
import {ProductUrl} from './productUrl';

export class Product {
  id: number;
  name: string;
  productDetails: ProductDetails;
  urls: ProductUrl[];
}
