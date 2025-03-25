import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { SensorsComponent } from "./sensors/sensors.component";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, SensorsComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  //title = 'webapp';
}
