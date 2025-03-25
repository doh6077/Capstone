import { Routes } from '@angular/router';
import { SensorListComponent } from './sensors/sensor-list/sensor-list.component';
import { SensorsComponent } from './sensors/sensors.component';

export const routes: Routes = [

    {'path':'', component:SensorsComponent},
    {'path':'view', component:SensorListComponent}

];
