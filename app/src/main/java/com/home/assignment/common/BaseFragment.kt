package com.home.assignment.common
import androidx.fragment.app.Fragment
import com.home.assignment.di.Injectable
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

open class BaseFragment :Fragment() , Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    /*@Inject
    lateinit var firebaseFirestore: FirebaseFirestore*/

    @Inject
    lateinit var utils: Utils

    /*@Inject
    lateinit var firebaseAuth: FirebaseAuth*/

    @Inject
    lateinit var commonMethods: CommonMethods



}
