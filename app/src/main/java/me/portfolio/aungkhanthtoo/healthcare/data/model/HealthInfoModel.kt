package me.portfolio.aungkhanthtoo.healthcare.data.model

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import me.portfolio.aungkhanthtoo.healthcare.data.vos.HealthcareInfo
import me.portfolio.aungkhanthtoo.healthcare.network.response.HealthResponse
import me.portfolio.aungkhanthtoo.healthcare.utils.ACCESS_TOKEN
import me.portfolio.aungkhanthtoo.healthcare.utils.EmptyError
import me.portfolio.aungkhanthtoo.healthcare.utils.Error
import me.portfolio.aungkhanthtoo.healthcare.utils.NetworkError

class HealthInfoModel private constructor(app: Application) : BaseModel(app) {
    companion object {
        private var INSTANCE: HealthInfoModel? = null

        fun getInstance(app: Application): HealthInfoModel {
            if (INSTANCE == null) {
                INSTANCE = HealthInfoModel(app)
            }
            return INSTANCE!!
        }
    }

    fun loadHealthInfos(
            dataLD: MutableLiveData<List<HealthcareInfo>>,
            errorLD: MutableLiveData<Error>) {

        observePersistence(dataLD)

        mAPI.fetchHealthInfos(ACCESS_TOKEN)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(object : SingleObserver<HealthResponse> {

                    override fun onSuccess(t: HealthResponse) {
                        Log.d("Database", "onSuccess${t.healthcareInfoList}")
                        val data = t.healthcareInfoList
                        if (data.isEmpty()) {
                            errorLD.postValue(EmptyError("Data is Empty"))
                            return
                        }
                        //dataLD.value=data
                        //dataLD.postValue(data)
                        persistData(data)
                    }

                    override fun onSubscribe(d: Disposable) {
                        // dispose
                    }

                    override fun onError(e: Throwable) {
                        errorLD.postValue(NetworkError(e.message!!))
                    }

                })
    }

    private fun persistData(data: List<HealthcareInfo>){
        Log.d("Database", "persisting data ${mDB.healthDao()}")
        mDB.healthDao().insertAll(data)
        Log.i("Database: ", "Save all data...${mDB.healthDao().getHealthInfo()}")
    }

    private fun observePersistence(data: MutableLiveData<List<HealthcareInfo>>){
        mDB.healthDao()
                .getHealthInfoLiveData()
                .observeForever {
                    data.postValue(it)
                }
    }
}