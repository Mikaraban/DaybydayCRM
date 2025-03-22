<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                aria-hidden="true">&times;</span></button>
    <h4 class="modal-title" id="myModalLabel">
        {{ __('Add Payment') }}</h4>
</div>
<form id="paymentForm" action="{{route('payment.add', [$invoice->external_id])}}" method="POST">
<div class="modal-body">
    <div class="row">
        <div class="col-lg-12">
            <p class="text-align-center" style="font-size:1.4em; font-weight: 300;">@lang('Amount due')</p>
            <p class="text-align-center lead" style="font-size:2.4em; line-height: 1; font-weight: 300;" id="amountDueFormatted">{{$amountDueFormatted}}</p>
            <!-- Ajout de l'élément pour l'erreur -->
            <p id="error-message" style="color: red; display: none; font-size: 1.2em;"></p>
            <hr>
        </div>
        <div class="col-lg-6">
            <div class="form-group col-lg-6 removeleft">
                <label for="amount" class="thin-weight">@lang('Amount in') {{$amountDue->getCurrency()->getCode()}}</label>
                <input type="number" step=".01" name="amount" id="amount" value="{{$amountDue->getBigDecimalAmount()}}" class="form-control input-sm">
            </div>
            <div class="form-group col-lg-12 removeleft">
                <label for="payment_date" class="thin-weight">@lang('Payment date')</label>
                <input type="date" name="payment_date" id="payment_date" data-value="{{today()->format(carbonDate())}}" class="form-control input-sm">
            </div>
            <div class="form-group col-lg-12 removeleft">
                <label for="source" class="thin-weight">@lang('Source')</label>
                <select name="source" id="source" class="form-control">
                    @foreach($paymentSources as $paymentSource)
                        <option value="{{$paymentSource->getSource()}}">{{$paymentSource->getDisplayValue()}}</option>
                    @endforeach
                </select>
            </div>
        </div>
        {{csrf_field()}}
        <div class="form-group col-lg-12">
            <label for="description" class="thin-weight">@lang('Description')</label>
            <textarea name="description" id="description" class="form-control"></textarea>
        </div>
    </div>
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-default col-lg-6" data-dismiss="modal">{{ __('Close') }}</button>
    <div class="col-lg-6">
        <input type="submit" value="{{__('Register payment')}}" class="btn btn-brand form-control closebtn" id="submitPayment">
    </div>
</div>
</form>

@push('scripts')
    <script>
        document.getElementById('paymentForm').addEventListener('submit', function(event) {
            event.preventDefault();

            const amountDueFormatted = parseFloat(
                document.getElementById('amountDueFormatted').innerText
                    .replace(/[^\d,.-]/g, '')
                    .replace(/\./g, '')
                    .replace(',', '.') 
            );

            const amount = parseFloat(document.getElementById('amount').value);
            console.log("inner due.inner text : "+ document.getElementById('amountDueFormatted').innerText);
            console.log("amount due : "+amountDueFormatted);
            console.log("amount inserer : "+amount);
            const errorMessage = document.getElementById('error-message');
            if (amount > amountDueFormatted) {
                // Afficher l'erreur en rouge
                errorMessage.textContent = '@lang("The entered amount exceeds the amount due. Please enter a valid amount.")';
                errorMessage.style.display = 'block';
            } else {
                errorMessage.style.display = 'none';
                this.submit();
            }
        });

        $('#payment_date').pickadate({
            hiddenName: true,
            format: "{{frontendDate()}}",
            formatSubmit: 'yyyy/mm/dd',
            closeOnClear: false,
        });
    </script>
@endpush

