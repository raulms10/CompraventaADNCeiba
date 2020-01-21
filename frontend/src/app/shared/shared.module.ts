import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { MatInputModule, MatFormFieldModule, MatIconModule, MatButtonModule, MatCardModule,
  MatTooltipModule, MatTableModule, MatPaginatorModule, MatSnackBarModule } from '@angular/material/';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CargandoComponent } from './components/cargando/cargando.component';
import { PopUpComponent } from './components/pop-up/pop-up.component';

@NgModule({
  declarations: [CargandoComponent, PopUpComponent],
  imports: [
    CommonModule,
    HttpClientModule
  ],
  exports: [
    CommonModule,
    HttpClientModule,
    MatInputModule,
    MatFormFieldModule,
    MatCardModule,
    MatIconModule,
    MatButtonModule,
    MatTooltipModule,
    MatTooltipModule,
    MatTableModule,
    MatPaginatorModule,
    MatSnackBarModule,
    FormsModule,
    ReactiveFormsModule,
    CargandoComponent,
    PopUpComponent
  ]
})
export class SharedModule { }
