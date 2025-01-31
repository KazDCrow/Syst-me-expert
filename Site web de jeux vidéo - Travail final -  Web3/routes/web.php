<?php

use App\Http\Controllers\JeuxController;
use Illuminate\Support\Facades\Route;

Route::get('/',[JeuxController::class, "index"])->name("jeux.accueil");

Route::get("/liste-Jeux", [JeuxController::class, "listeJeux"])->name("jeux.liste");
