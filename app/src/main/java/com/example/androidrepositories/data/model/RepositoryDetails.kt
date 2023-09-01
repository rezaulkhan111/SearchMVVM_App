package com.example.androidrepositories.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "RepositoryDetails")
class RepositoryDetails {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "repositoryTableId")
    @Transient
    private var repositoryTableId: Long? = null

    @ColumnInfo(name = "viewType")
    private var viewType: Int? = null

    @SerializedName("id")
    @Expose
    private var id: Int? = null

    @SerializedName("node_id")
    @Expose
    private var nodeId: String? = null

    @SerializedName("name")
    @Expose
    private var name: String? = null

    @SerializedName("full_name")
    @Expose
    private var fullName: String? = null

    @SerializedName("private")
    @Expose
    private var _private: Boolean? = null

    @SerializedName("owner")
    @Expose
    @Ignore
    private var owner: OwnerDetails? = null

    @SerializedName("description")
    @Expose
    private var description: String? = null

    @SerializedName("fork")
    @Expose
    private var fork: Boolean? = null

    @SerializedName("created_at")
    @Expose
    private var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    private var updatedAt: String? = null

    @SerializedName("pushed_at")
    @Expose
    private var pushedAt: String? = null


    @SerializedName("size")
    @Expose
    private var size: Int? = null

    @SerializedName("stargazers_count")
    @Expose
    private var stargazersCount: Int? = null

    @SerializedName("watchers_count")
    @Expose
    private var watchersCount: Int? = null

    @SerializedName("forks_count")
    @Expose
    private var forksCount: Int? = null

    @SerializedName("open_issues_count")
    @Expose
    private var openIssuesCount: Int? = null

    @SerializedName("forks")
    @Expose
    private var forks: Int? = null

    @SerializedName("open_issues")
    @Expose
    private var openIssues: Int? = null

    @SerializedName("watchers")
    @Expose
    private var watchers: Int? = null

    @SerializedName("score")
    @Expose
    private var score: Double? = null

    @Expose
    @ColumnInfo(name = "ownerId")
    private var ownerId: Int? = null

    fun getRepositoryTableId(): Long? {
        return repositoryTableId
    }

    fun setRepositoryTableId(repositoryTableId: Long?) {
        this.repositoryTableId = repositoryTableId
    }

    fun getViewType(): Int? {
        return viewType
    }

    fun setViewType(viewType: Int?) {
        this.viewType = viewType
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

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getFullName(): String? {
        return fullName
    }

    fun setFullName(fullName: String?) {
        this.fullName = fullName
    }

    fun getPrivate(): Boolean? {
        return _private
    }

    fun setPrivate(_private: Boolean?) {
        this._private = _private
    }

    fun getOwner(): OwnerDetails? {
        return owner
    }

    fun setOwner(owner: OwnerDetails?) {
        this.owner = owner
    }

    fun getDescription(): String? {
        return description
    }

    fun setDescription(description: String?) {
        this.description = description
    }

    fun getFork(): Boolean? {
        return fork
    }

    fun setFork(fork: Boolean?) {
        this.fork = fork
    }

    fun getCreatedAt(): String? {
        return createdAt
    }

    fun setCreatedAt(createdAt: String?) {
        this.createdAt = createdAt
    }

    fun getUpdatedAt(): String? {
        return updatedAt
    }

    fun setUpdatedAt(updatedAt: String?) {
        this.updatedAt = updatedAt
    }

    fun getPushedAt(): String? {
        return pushedAt
    }

    fun setPushedAt(pushedAt: String?) {
        this.pushedAt = pushedAt
    }

    fun getSize(): Int? {
        return size
    }

    fun setSize(size: Int?) {
        this.size = size
    }

    fun getStargazersCount(): Int? {
        return stargazersCount
    }

    fun setStargazersCount(stargazersCount: Int?) {
        this.stargazersCount = stargazersCount
    }

    fun getWatchersCount(): Int? {
        return watchersCount
    }

    fun setWatchersCount(watchersCount: Int?) {
        this.watchersCount = watchersCount
    }

    fun getForksCount(): Int? {
        return forksCount
    }

    fun setForksCount(forksCount: Int?) {
        this.forksCount = forksCount
    }

    fun getOpenIssuesCount(): Int? {
        return openIssuesCount
    }

    fun setOpenIssuesCount(openIssuesCount: Int?) {
        this.openIssuesCount = openIssuesCount
    }

    fun getForks(): Int? {
        return forks
    }

    fun setForks(forks: Int?) {
        this.forks = forks
    }

    fun getOpenIssues(): Int? {
        return openIssues
    }

    fun setOpenIssues(openIssues: Int?) {
        this.openIssues = openIssues
    }

    fun getWatchers(): Int? {
        return watchers
    }

    fun setWatchers(watchers: Int?) {
        this.watchers = watchers
    }

    fun getScore(): Double? {
        return score
    }

    fun setScore(score: Double?) {
        this.score = score
    }

    fun getOwnerId(): Int? {
        return ownerId
    }

    fun setOwnerId(ownerId: Int?) {
        this.ownerId = ownerId
    }
}