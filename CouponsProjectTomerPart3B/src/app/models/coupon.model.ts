import { Company } from './company.model';

export class Coupon {
  constructor(
    public id?: string,
    public company?: Company,
    public category?: string,
    public title?: string,
    public description?: string,
    public startDate?: string,
    public endDate?: string,
    public amount?: string,
    public price?: string,
    public image?: string
  ) {}
}
