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

class PagesController extends Controller
{
    /**
     * Dashobard view
     * @return mixed
     */
    public function dashboard()
    {
        $today = today();
        $startDate = today()->subdays(14);
        $period = CarbonPeriod::create($startDate, $today);
        $datasheet = [];

        // Iterate over the period
        foreach ($period as $date) {
            $datasheet[$date->format(carbonDate())] = [];
            $datasheet[$date->format(carbonDate())]["monthly"] = [];
            $datasheet[$date->format(carbonDate())]["monthly"]["tasks"] = 0;
            $datasheet[$date->format(carbonDate())]["monthly"]["leads"] = 0;
        }

        $tasks = Task::whereBetween('created_at', [$startDate, now()])->get();
        $leads = Lead::whereBetween('created_at', [$startDate, now()])->get();
        foreach ($tasks as $task) {
            $datasheet[$task->created_at->format(carbonDate())]["monthly"]["tasks"]++;
        }

        foreach ($leads as $lead) {
            $datasheet[$lead->created_at->format(carbonDate())]["monthly"]["leads"]++;
        }
        if (!auth()->user()->can('absence-view')) {
            $absences = [];
        } else {
            $absences = Absence::with('user')->groupBy('user_id')->where('start_at', '>=', today())->orWhere('end_at', '>', today())->get();
        }

        return view('pages.dashboard')
            ->withUsers(User::with(['department'])->get())
            ->withDatasheet($datasheet)
            ->withTotalTasks(Task::count())
            ->withTotalLeads(Lead::count())
            ->withTotalProjects(Project::count())
            ->withTotalClients(Client::count())
            ->withSettings(Setting::first())
            ->withAbsencesToday($absences);
    }

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
        return response()->json($data);
    }

    /**
     * Redirection vers localhost:7070
     * @return RedirectResponse
     */
    public function redirectToNewApp()
    {
        // Redirige vers localhost:7070
        return redirect()->to('http://localhost:7070');
    }
}
