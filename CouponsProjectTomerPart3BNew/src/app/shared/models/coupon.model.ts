import { Company } from './company.model';

export interface Coupon {
  id: string;
  company: Company;
  category: string;
  title: string;
  description: string;
  startDate: Date;
  endDate: Date;
  amount: string;
  price: string;
  image: string;
}
