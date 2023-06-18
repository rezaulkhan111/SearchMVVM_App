package com.example.androidrepositories.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "OwnerDetails")
class OwnerDetails {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ownerTableId")
    @Transient
    private var ownerTableId: Long? = null

    @SerializedName("login")
    @Expose
    private var login: String? = null

    @SerializedName("id")
    @Expose
    private var id: Int? = null

    @SerializedName("node_id")
    @Expose
    private var nodeId: String? = null

    @SerializedName("avatar_url")
    @Expose
    private var avatarUrl: String? = null

    @SerializedName("type")
    @Expose
    private var type: String? = null

    @SerializedName("site_admin")
    @Expose
    private var siteAdmin: Boolean? = null

    fun getOwnerTableId(): Long? {
        return ownerTableId
    }

    fun setOwnerTableId(ownerTableId: Long?) {
        this.ownerTableId = ownerTableId
    }

    fun getLogin(): String? {
        return login
    }

    fun setLogin(login: String?) {
        this.login = login
    }

    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
        this.id = id
    }

    fun getNodeId(): String? {
        return nodeId
    }

    fun setNodeId(nodeId: String?) {
        this.nodeId = nodeId
    }

    fun getAvatarUrl(): String? {
        return avatarUrl
    }

    fun setAvatarUrl(avatarUrl: String?) {
        this.avatarUrl = avatarUrl
    }

    fun getType(): String? {
        return type
    }

    fun setType(type: String?) {
        this.type = type
    }

    fun getSiteAdmin(): Boolean? {
        return siteAdmin
    }

    fun setSiteAdmin(siteAdmin: Boolean?) {
        this.siteAdmin = siteAdmin
    }
}