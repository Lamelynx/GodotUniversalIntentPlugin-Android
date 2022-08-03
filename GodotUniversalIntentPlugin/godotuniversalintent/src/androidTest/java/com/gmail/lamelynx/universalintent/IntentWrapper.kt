package com.gmail.lamelynx.universalintent

import android.Manifest
import android.content.*
import android.content.Intent.FilterComparison
import android.content.Intent.ShortcutIconResource
import android.content.pm.ActivityInfo
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.content.res.Resources
import android.content.res.TypedArray
import android.graphics.Rect
import android.media.AudioManager
import android.net.Uri
import android.os.*
import android.os.Parcelable.Creator
import android.os.storage.StorageManager
import android.provider.ContactsContract.QuickContact
import android.provider.MediaStore
import android.text.TextUtils
import android.util.ArraySet
import android.util.AttributeSet
import android.util.Log
import android.view.Menu
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlSerializer
import java.io.File
import java.io.IOException
import java.io.PrintWriter
import java.io.Serializable
import java.lang.ClassCastException
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException
import java.lang.IndexOutOfBoundsException
import java.lang.NumberFormatException
import java.lang.RuntimeException
import java.lang.StringBuilder
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.net.URISyntaxException
import java.util.*

