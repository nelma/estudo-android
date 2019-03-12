package com.example.estudoandroid

import android.os.Parcel
import android.os.Parcelable

class UsuarioParcelable : Parcelable {
    var nome: String? = null
    var sobrenome: String? = null

    constructor(nome: String, sobrenome: String) {
        this.nome = nome
        this.sobrenome = sobrenome
    }

    protected constructor(`in`: Parcel) {
        nome = `in`.readString()
        sobrenome = `in`.readString()
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(nome)
        dest.writeString(sobrenome)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        val CREATOR: Parcelable.Creator<UsuarioParcelable> = object : Parcelable.Creator<UsuarioParcelable> {
            override fun createFromParcel(`in`: Parcel): UsuarioParcelable {
                return UsuarioParcelable(`in`)
            }

            override fun newArray(size: Int): Array<UsuarioParcelable?> {
                return arrayOfNulls(size)
            }
        }
    }
}