import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent }  from './app.component';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

import {ReactiveFormsModule,FormBuilder} from '@angular/forms';
@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        HttpClientModule,
        ReactiveFormsModule,
     
        
    ],
    declarations: [
        AppComponent,
        
       
		],
    providers: [],
    bootstrap: [AppComponent]
})

export class AppModule { }