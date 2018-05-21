export interface AccomodationType {
  id: number;
  type: string;
}

export interface Category {
  id: number;
  category: string;
}


export interface AdditionalService {
  id: number;
  name: string;
}

export interface Filter {
  types: AccomodationType[];
  categories: Category[];
  services: AdditionalService[];
}
