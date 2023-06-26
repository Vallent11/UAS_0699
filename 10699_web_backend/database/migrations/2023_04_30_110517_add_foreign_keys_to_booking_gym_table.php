<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::table('booking_gym', function (Blueprint $table) {
            $table->foreign(['id_sesi_gym'], 'booking_gym_ibfk_2')->references(['id_sesi_gym'])->on('sesi_gym');
            $table->foreign(['id_member'], 'booking_gym_ibfk_1')->references(['id_member'])->on('member');
            $table->foreign(['no_struk_transaksi'], 'booking_gym_ibfk_3')->references(['no_struk_transaksi'])->on('transaksi_member');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::table('booking_gym', function (Blueprint $table) {
            $table->dropForeign('booking_gym_ibfk_2');
            $table->dropForeign('booking_gym_ibfk_1');
            $table->dropForeign('booking_gym_ibfk_3');
        });
    }
};
