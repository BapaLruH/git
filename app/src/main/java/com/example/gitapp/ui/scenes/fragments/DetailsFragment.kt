package com.example.gitapp.ui.scenes.fragments

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.gitapp.R
import com.example.gitapp.databinding.FragmentDetailsBinding
import com.example.gitapp.ui.scenes.model.UserDetails
import com.example.gitapp.ui.scenes.viewmodels.DetailsPageViewModel

class DetailsFragment : Fragment() {

    private var viewBinding: FragmentDetailsBinding? = null

    private val args: DetailsFragmentArgs by navArgs()
    private val detailsPageViewModel: DetailsPageViewModel by viewModels {
        ViewModelProvider.AndroidViewModelFactory.getInstance(
            Application()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentDetailsBinding.bind(view)

        detailsPageViewModel.getUserDetailsLiveData(args.login)
            .observe(viewLifecycleOwner, Observer {
                setUserData(it)
            })
        detailsPageViewModel.loadingLiveData.observe(viewLifecycleOwner, Observer {
            updateProgressVisibility(it)
        })
    }

    override fun onDestroy() {
        viewBinding = null
        super.onDestroy()
    }

    private fun updateProgressVisibility(visible: Boolean) {
        updateViewVisibleStatus(
            viewBinding?.pbLoading, when {
                visible -> 1
                else -> 0
            }
        )
    }

    private fun setUserData(userDetails: UserDetails) {
        viewBinding?.tvLogin?.text =
            String.format(getString(R.string.login_text), userDetails.login)
        viewBinding?.tvName?.text = userDetails.name

        updateViewVisibleStatus(viewBinding?.groupRepos, userDetails.publicRepos)
        viewBinding?.tvReposSize?.text = userDetails.publicRepos.toString()
        updateViewVisibleStatus(viewBinding?.groupGists, userDetails.publicGists)
        viewBinding?.tvGistsSize?.text = userDetails.publicGists.toString()
        updateViewVisibleStatus(viewBinding?.groupFollowers, userDetails.followers)
        viewBinding?.tvFollowersSize?.text = userDetails.followers.toString()
        updateViewVisibleStatus(viewBinding?.groupFollowing, userDetails.following)
        viewBinding?.tvFollowingSize?.text = userDetails.following.toString()

        userDetails.bio?.length.let { updateViewVisibleStatus(viewBinding?.groupBiography, it) }
        userDetails.bio.let {
            viewBinding?.tvBio?.text = it
        }

        updateViewVisibleStatus(viewBinding?.groupCreatedDate, userDetails.createdAt.length)
        viewBinding?.tvCreatedDate?.text = userDetails.createdAt
        updateViewVisibleStatus(viewBinding?.groupUpdatedDate, userDetails.updatedAt.length)
        viewBinding?.tvUpdatedDate?.text = userDetails.updatedAt

        viewBinding?.ivAvatar?.let {
            Glide.with(this)
                .load(userDetails.avatar)
                .apply(RequestOptions().transform(RoundedCorners(16)))
                .placeholder(R.drawable.github_logo).into(it)
        }

        userDetails.htmlUrl?.length.let { updateViewVisibleStatus(viewBinding?.ivGit, it) }
        viewBinding?.ivGit?.setOnClickListener {
            userDetails.htmlUrl?.let { link -> openLink(link) }
        }

        userDetails.blog?.length.let { updateViewVisibleStatus(viewBinding?.ivBlog, it) }
        viewBinding?.ivBlog?.setOnClickListener {
            userDetails.blog?.let { link -> openLink(link) }
        }

        userDetails.twitter_username?.length.let {
            updateViewVisibleStatus(
                viewBinding?.ivTweeter,
                it
            )
        }
        viewBinding?.ivTweeter?.setOnClickListener {
            userDetails.twitter_username?.let { link ->
                openLink(
                    String.format(
                        getString(R.string.twitter_url),
                        link
                    )
                )
            }
        }
    }

    private fun openLink(link: String) {
        run {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
            if (context?.packageManager?.let { it1 -> intent.resolveActivity(it1) } != null)
                startActivity(intent)
            else
                Log.d(tag, getString(R.string.error))
        }
    }

    private fun updateViewVisibleStatus(group: View?, size: Int?) {
        if (size == null) {
            group?.visibility = View.GONE
            return
        }

        group?.visibility = when {
            size > 0 -> View.VISIBLE
            else -> View.GONE
        }
    }
}