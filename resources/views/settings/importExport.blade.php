@extends('layouts.master')

@section('content')

@push('scripts')
    <script>
        $(document).ready(function () {
            $('[data-toggle="tooltip"]').tooltip(); // Tooltip on icons top

            $('.popoverOption').each(function () {
                var $this = $(this);
                $this.popover({
                    trigger: 'hover',
                    placement: 'left',
                    container: $this,
                    html: true
                });
            });
        });
    </script>
@endpush

<h2>Export / Import Data</h2>

<!-- Export Section -->
<div class="mb-4">
    <h4>Export Data</h4>

    {!! Form::open(['route' => 'export.excel', 'method' => 'GET']) !!}
        <div class="form-group">
            {!! Form::label('table_name', 'Select Table to Export') !!}
            {!! Form::select('table_name', $tableNames ?? [], null, ['class' => 'form-control', 'required' => true]) !!}
            
            @if (empty($tableNames))
                <div class="alert alert-warning mt-2">
                    No tables available in the database. Please check your database connection.
                </div>
            @endif
        </div>

        <div class="form-group">
            {!! Form::submit('Export to Excel', ['class' => 'btn btn-success']) !!}
        </div>
    {!! Form::close() !!}

    {!! Form::open(['route' => 'export.csv', 'method' => 'GET']) !!}
        <div class="form-group">
            {!! Form::label('table_name', 'Select Table to Export') !!}
            {!! Form::select('table_name', $tableNames ?? [], null, ['class' => 'form-control', 'required' => true]) !!}
            
            @if (empty($tableNames))
                <div class="alert alert-warning mt-2">
                    No tables available in the database. Please check your database connection.
                </div>
            @endif
        </div>

        <div class="form-group">
            {!! Form::submit('Export to CSV', ['class' => 'btn btn-primary']) !!}
        </div>
    {!! Form::close() !!}
</div>

<!-- Import Section -->
<div>
    <h4>Import CSV</h4>

    {!! Form::open(['route' => 'import.csv', 'files' => true, 'method' => 'POST']) !!}
        <div class="form-group">
            {!! Form::label('csv_file', 'Select CSV File to Import') !!}
            {!! Form::file('csv_file', ['class' => 'form-control', 'required' => true]) !!}
        </div>

        <div class="form-group">
            {!! Form::label('table_name', 'Target Table') !!}
            {!! Form::select('table_name', $tableNames ?? [], null, ['class' => 'form-control', 'required' => true]) !!}

            @if (empty($tableNames))
                <div class="alert alert-warning mt-2">
                    No tables available in the database. Please check your database connection.
                </div>
            @endif
        </div>

        <div class="form-group">
            {!! Form::submit('Import CSV', ['class' => 'btn btn-warning']) !!}
        </div>
    {!! Form::close() !!}
</div>

@if(session('success'))
    <div class="alert alert-success mt-3">
        {{ session('success') }}
    </div>
@endif

@if(session('error'))
    <div class="alert alert-danger mt-3">
        {{ session('error') }}
    </div>
@endif

@stop
