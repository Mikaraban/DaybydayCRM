<?php
namespace App\Http\Controllers;

use App\Models\Absence;
use App\Models\Client;
use App\Models\Lead;
use App\Models\Project;
use App\Models\Setting;
use App\Models\Task;
use App\Models\User;
use App\Models\Offer;
use App\Models\Payment;
use App\Models\Invoice;
use Carbon\Carbon;
use Carbon\CarbonPeriod;
use DB;

class ForNewAppController extends Controller
{

    public function getTotals()
    {
        // Récupérer les données de la base de données
        $data = [
            'totalClients' => Client::count(),
            'totalProjects' => Project::count(),
            'totalTasks' => Task::count(),
            'totalOffers' => Offer::count(),
            'totalInvoices' => Invoice::count(),
            'totalPayments' => Payment::sum('amount'),
        ];

        // Retourner les données au format JSON
        return response()->json($data)
        ->header('Content-Type', 'application/json');
    }
    public function getAllClientsJSON()
    {
        // Récupère tous les clients depuis la base de données
        $clients = Client::all();

        // Retourne les clients en format JSON
        return response()->json($clients)
        ->header('Content-Type', 'application/json');
    }
    public function getAllInvoicesJSON()
    {
        $invoices = Invoice::all();
        return response()->json($invoices)
        ->header('Content-Type', 'application/json');
    }
    public function getAllOffersJSON()
    {
        $offers = Offer::all();
        return response()->json($offers)
        ->header('Content-Type', 'application/json');
    }
    public function getAllPaymentsJSON()
    {
        $payment = Payment::all();
        return response()->json($payment)
        ->header('Content-Type', 'application/json');
    }
    public function getAllTasksJSON()
    {
        $tasks = Task::all();
        return response()->json($tasks)
        ->header('Content-Type', 'application/json');
    }


    public function destroyPaymentWithId($payment_id)
    {
        // Vérifiez si l'utilisateur a les permissions nécessaires
        if (!auth()->user()->can('payment-delete')) {
            session()->flash('flash_message', __("You don't have permission to delete a payment"));
            return redirect()->back();
        }

        // Rechercher le paiement à l'aide des deux IDs
        $payment = Payment::where('id', $payment_id)
                        ->first();

        // Si le paiement n'existe pas, retournez une erreur
        if (!$payment) {
            session()->flash('flash_message', __("Payment not found or invalid IDs provided"));
            return redirect()->back();
        }

        // Initialiser l'intégration pour supprimer le paiement via une API externe
        $api = Integration::initBillingIntegration();
        if ($api) {
            $api->deletePayment($payment);
        }

        // Supprimer le paiement de la base de données
        $payment->delete();

        // Afficher un message de succès
        session()->flash('flash_message', __('Payment successfully deleted'));
        return redirect()->back();
    }
}
