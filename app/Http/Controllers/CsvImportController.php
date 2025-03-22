<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Artisan;
use Illuminate\Support\Facades\DB;

class CsvImportController extends Controller
{
    /**
     * Show the CSV import form with available tables.
     *
     * @return \Illuminate\View\View
     */
    public function showImportForm()
    {
        // Récupérer la liste des tables de la base de données
        $tables = DB::select('SHOW TABLES');
        $tableNames = array_map(function ($table) {
            return $table->Tables_in_daybyday; // Remplacez 'your_database_name' par le nom de votre base de données
        }, $tables);

        return view('settings.importExport', compact('tableNames')); // Passez les noms des tables à la vue
    }

    /**
     * Handle the CSV import request.
     *
     * @param Request $request
     * @return \Illuminate\Http\JsonResponse
     */
    public function import(Request $request)
    {
        // Valider la demande
        $request->validate([
            'file' => 'required|file|mimes:csv,txt',
            'table' => 'required|string',
        ]);

        // Récupérer le fichier et le sauvegarder dans un emplacement temporaire
        $file = $request->file('file');
        $filePath = $file->storeAs('csv_uploads', $file->getClientOriginalName());

        // Exécuter la commande artisan pour l'importation
        $exitCode = Artisan::call('import:csv', [
            'filePath' => storage_path('app/' . $filePath),
            'tableName' => $request->input('table'),
        ]);

        // Retourner une réponse en fonction du résultat
        if ($exitCode === 0) {
            return response()->json([
                'success' => true,
                'message' => 'CSV data imported successfully!',
            ]);
        } else {
            return response()->json([
                'success' => false,
                'message' => 'Error importing CSV data.',
            ], 500);
        }
    }
}
