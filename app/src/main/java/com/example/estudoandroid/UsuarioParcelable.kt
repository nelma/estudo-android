package com.example.estudoandroid

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UsuarioParcelable(val nome: String, val sobrenome: String): Parcelable