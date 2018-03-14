export class WeatherInfo {
  id: string;
  userId: string;
  finished: string;
  city: string;
  date: string;
  temp: string;
  pressure: string;
  description: string;
  lon: string;
  lat: string;
  type: string;
}

export class QueueInfo {
  pos: string;
}

export class ErrorInfo {
  message: string;
}

export interface Info {
}

export class InfoResponse {
  type: string;
  info: Info;
}
