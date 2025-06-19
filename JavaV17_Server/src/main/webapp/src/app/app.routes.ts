import { Routes } from '@angular/router';
import { SensorListComponent } from './sensors/sensor-list/sensor-list.component';
import { SensorsComponent } from './sensors/sensors.component';
import { SensorHistoryComponent } from './sensors/sensor-history/sensor-history.component';
import { CleanerAddComponent } from './cleaners/cleaner-add/cleaner-add.component';
import { CleanerListComponent } from './cleaners/cleaner-list/cleaner-list.component';
import { CleanersComponent } from './cleaners/cleaners.component';
import { TrashbinsListComponent } from './trashbins/trashbins-list/trashbins-list.component';
import { TrashbinsAddComponent } from './trashbins/trashbins-add/trashbins-add.component';
export const routes: Routes = [

    {'path':'sensors', component:SensorsComponent},
    {'path':'cleaners', component:CleanersComponent},
    {'path':'view/sensors', component:SensorListComponent},
    { 'path' :'view/sensors/:id/history', component: SensorHistoryComponent },
    { path: 'view/cleaners', component: CleanerListComponent },
    { path: 'add/cleaners', component: CleanerAddComponent },
    { path: 'view/trashbins', component: TrashbinsListComponent },
    { path: 'add/trashbins', component: TrashbinsAddComponent },


];
