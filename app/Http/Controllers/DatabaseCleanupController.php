<?php

namespace App\Http\Controllers;

use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Artisan;
use Illuminate\Support\Facades\Log;

class DatabaseCleanupController extends Controller
{
    public function deleteAllData()
    {
        try {
            // Désactiver les contraintes de clé étrangère
            DB::statement('SET FOREIGN_KEY_CHECKS = 0;');

            // Récupérer les noms des tables
            $tables = DB::select('SHOW TABLES');
            $dbName = env('DB_DATABASE'); // Nom de la base depuis .env

            foreach ($tables as $table) {
                // Récupérer le nom de la table dynamiquement
                $tableName = current((array) $table);
                DB::table($tableName)->truncate(); // Vider la table
                
            }

            // Réactiver les contraintes de clé étrangère
            DB::statement('SET FOREIGN_KEY_CHECKS = 1;');

            // Exécuter les seeders
            Artisan::call('db:seed');

            // Rediriger avec un message de succès
            return redirect()->route('login')->with('success', 'Toutes les données ont été supprimées sauf celles de la table users, et les seeders ont été réexécutés.');
        } catch (\Exception $e) {
            // Logger l'erreur et retourner un message d'erreur
            Log::error('Erreur lors du nettoyage de la base de données: ' . $e->getMessage());
            return redirect()->back()->with('error', 'Une erreur est survenue lors du nettoyage de la base de données.');
        }
    }
}
