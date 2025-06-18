    package Models;

    public class File implements Node{
        private String name;
        private String content;
        public File(String name,String content){
            this.name=name;
            this.content=content;
        }
        @Override
        public Node deepCopy(){
            return new File(this.name,this.content);
        }
        @Override
        public String getName(){
            return name;
        }
        public void setName(String name){
            this.name=name;
        }
        public String getContent(){
            return content;
        }
        public void setContent(String content){
            this.content=content;
        }

        @Override
        public String toString() {
            return "Models.File{" +
                    "name='" + name + '\'' +
                    ", content='" + content + '\'' +
                    '}';
        }
    }
