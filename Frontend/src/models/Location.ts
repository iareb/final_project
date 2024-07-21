export class Location {

  id: number;
  name: string;
  country: string;
  latitude: number;
  longitude: number;

  constructor(id: number, name: string, country: string, latitude: number, longitude: number) {
    this.id = id;
    this.name = name;
    this.country = country;
    this.latitude = latitude;
    this.longitude = longitude;
  }

}
