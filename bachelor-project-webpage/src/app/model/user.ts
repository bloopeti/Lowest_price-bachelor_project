import {Product} from './product';

export class User {
  id: number;
  emailAddress: string;
  password: string;
  passNoHash: string;
  passNoHashRepeat: string;
  isAdmin: number;
  favouriteProducts: Product[];
}
