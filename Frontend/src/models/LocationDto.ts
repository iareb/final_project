export class LocationDto {

  private name: string;
  private country: string;
  private latitude: number;
  private longitude: number;

  constructor(name: string, country: string, latitude: number, longitude: number) {
    this.name = name;
    this.country = country;
    this.latitude = latitude;
    this.longitude = longitude;
  }

}
