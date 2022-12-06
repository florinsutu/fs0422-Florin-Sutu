import { Injectable } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { FileHandle } from '../models/file-model';
import { Post } from '../models/post';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class ImageProcessingService {

  constructor(private sanitizer: DomSanitizer) { }

  public createImages(x: Post|User) {

    if(x.image){
      const img = x.image

      const imgBlob = this.dataURItoBlob(img.imgBytes, img.type)

      const imgFile = new File([imgBlob], img.name, { type: img.type })

      const imgFileHandle: FileHandle = {
        file: imgFile,
        url: this.sanitizer.bypassSecurityTrustUrl(window.URL.createObjectURL(imgFile))
      }

      x.image = imgFileHandle
    }else{

      let safeUrl = "https://www.worldfuturecouncil.org/wp-content/uploads/2020/06/blank-profile-picture-973460_1280-1-705x705.png"

      x.image = {
        url: this.sanitizer.bypassSecurityTrustUrl(safeUrl)
      }

    }


    return x;
  }

  public dataURItoBlob(picBytes: any, imageType: any) {

    const byteString = window.atob(picBytes)
    const arrayBuffer = new ArrayBuffer(byteString.length)
    const int8Array = new Uint8Array(arrayBuffer)

    for (let i = 0; i < byteString.length; i++) {
      int8Array[i] = byteString.charCodeAt(i)
    }

    const blob = new Blob([int8Array], { type: imageType })
    return blob

  }
}
