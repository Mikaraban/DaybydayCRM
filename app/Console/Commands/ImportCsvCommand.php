<?php

namespace App\Console\Commands;

use Illuminate\Console\Command;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\File;

class ImportCsvCommand extends Command
{
    /**
     * The name and signature of the console command.
     *
     * @var string
     */
    protected $signature = 'import:csv {filePath} {tableName}';

    /**
     * The console command description.
     *
     * @var string
     */
    protected $description = 'Import CSV data into a database table';

    /**
     * Execute the console command.
     *
     * @return int
     */
    public function handle()
    {
        $filePath = $this->argument('filePath');
        $tableName = $this->argument('tableName');

        if (!File::exists($filePath)) {
            $this->error("File not found: $filePath");
            return 1;
        }

        $csvData = array_map('str_getcsv', file($filePath));
        $header = array_shift($csvData); // Get the first row as the header

        if (empty($header)) {
            $this->error('The CSV file is empty or improperly formatted.');
            return 1;
        }

        foreach ($csvData as $row) {
            $data = array_combine($header, $row);

            if ($data === false) {
                $this->error('Error combining header and row data.');
                return 1;
            }

            try {
                DB::table($tableName)->insert($data);
            } catch (\Exception $e) {
                $this->error('Error inserting data: ' . $e->getMessage());
                return 1;
            }
        }

        $this->info("Data from $filePath successfully imported into $tableName.");
        return 0;
    }
}
